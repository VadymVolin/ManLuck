
document.querySelector('[data-action-addColumn]')
	.addEventListener('click', function (event) {
		const columnElement = new Column();
		App.save();
	});

const App = {
	save() {
		const object = {
			columns: {
				idCounter: Column.idCount,
				title: '',
				items: []
			},
			notes: {
				idCounter: Note.idCount,
				items: []
			}
		};

		document.querySelectorAll('.column')
			.forEach(columnElement => {
				const column = {
					id: parseInt(columnElement.getAttribute('data-column-id')),
					title: columnElement.querySelector('.column-header').textContent,
					notesId: []
				};

				columnElement.querySelectorAll('.note').forEach(noteElement => {
					column.notesId.push(parseInt(noteElement.getAttribute('data-note-id')));
				});

				object.columns.items.push(column);
			});

		document.querySelectorAll('.note')
			.forEach(noteElement => {
				const note = {
					id: parseInt(noteElement.getAttribute('data-note-id')),
					content: noteElement.textContent
				};
				object.notes.items.push(note);
			});

		const json = JSON.stringify(object);
		localStorage.setItem('kanban-desk', json);
	},

	load() {
		if (!localStorage.getItem('kanban-desk')) {
			return;
		}

		const object = JSON.parse(localStorage.getItem('kanban-desk'));
		console.log(object);
		const getNoteById = id => object.notes.items.find(note => note.id === id);
		let columns = document.querySelector('.columns');
		columns.innerHTML = '';



		for (const { id, title, notesId } of object.columns.items) {
			const columnElement = new Column(id, title);
			Note.idCount = Math.max.apply(null, notesId);
			for (const noteId of notesId) {
				const { id, content } = getNoteById(noteId);
				const note = new Note(id, content);
				columnElement.add(note);
			}

			columns.append(columnElement.element);
		}


	}
};

App.load();
