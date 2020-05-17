class Column {
    constructor(id = null, title = null) {
        const instance = this;

        this.notes = [];

        const element = this.element = document.createElement('div');
        element.classList.add('column');
        element.setAttribute('draggable', 'true');
        element.innerHTML =
            `
        <div class="c-header">
        <p class="column-header">
        </p>
        <span class="column-remove">&#10006;</span>
        </div>
        <p class="column-header1">
        <span data-action-addNote class="action">&#10010; cart</span>
        </p>
        <div data-notes class="column-body">
        </div>
        `;
        let header = element.querySelector('.column-header');
        this.editProcess(header);

        let removeSpan = element.querySelector('.column-remove');
        this.remove(removeSpan, element);

        if (id) {
            element.setAttribute('data-column-id', id);
            document.querySelector('.columns').append(element);
            header.textContent = title;
            if (id > Column.idCount) {
                Column.idCount = id;
            } else {

            }
        } else {
            Column.idCount += 1;
            element.setAttribute('data-column-id', Column.idCount);
            title = 'Your plan'
            document.querySelector('.columns').append(element);
            header.setAttribute('contenteditable', 'true');
            header.focus();
        }
        const spanActionAddNote = element.querySelector('[data-action-addNote]');

        spanActionAddNote.addEventListener('click', function (event) {
            const noteElement = new Note;
            instance.add(noteElement);
            noteElement.element.setAttribute('contenteditable', 'true');
            noteElement.element.focus();
        });
        element.addEventListener('dragstart', this.dragstart.bind(this));
        element.addEventListener('dragend', this.dragend.bind(this));
        element.addEventListener('dragover', this.dragover.bind(this));
        element.addEventListener('drop', this.drop.bind(this));
    }


    add(...notes) {
        for (const note of notes) {
            if (!this.notes.includes(note)) {
                this.notes.push(note);

                this.element.querySelector('[data-notes]').append(note.element);
            }
        }
    }

    editProcess(titleElement = null) {
        titleElement.addEventListener('dblclick', function (event) {
            titleElement.setAttribute('contenteditable', 'true');
            titleElement.focus();
        });
        titleElement.addEventListener('blur', function (event) {
            if (titleElement.textContent.trim().length) {
                titleElement.removeAttribute('contenteditable');
            } else {
                titleElement.textContent = 'Title';
            }
            App.save();
        });
    }

    remove(removeElement = null, element = null) {
        removeElement.addEventListener('click', function (event) {
            element.remove();
            App.save();
        });
    }

    dragstart(event) {
        Column.dragged = this.element;
        Column.dragged.classList.add('dragged');
        event.stopPropagation();

        document.querySelectorAll('.note')
            .forEach(noteElement => noteElement.removeAttribute('draggable'));
    }

    dragend(event) {
        Column.dragged.classList.remove('dragged');
        Column.dragged = null;
        Column.dropped = null;
        document.querySelectorAll('.note')
            .forEach(noteElement => noteElement.setAttribute('draggable', 'true'));
        document.querySelectorAll('.column')
            .forEach(columnElement => columnElement.classList.remove('under'));
        App.save();
    }

    dragover(event) {
        event.preventDefault();
        if (Column.dragged) {
            if (Column.dropped) {
                Column.dropped.classList.remove('under');
            }
            Column.dropped = null;
        }
        if (!Column.dragged || Column.dragged === this.element) {
            return;
        }
        Column.dropped = this.element;
        document.querySelectorAll('.column').forEach(element => element.classList.remove('under'));
        this.element.classList.add('under');
    }

    drop(event) {
        if (Note.dragged) {
            return this.element
                .querySelector('[data-notes]')
                .append(Note.dragged);
        }
        if (Column.dragged) {
            const children = Array.from(document.querySelector('.columns').children);
            const indexA = children.indexOf(this.element);
            const indexB = children.indexOf(Column.dragged)
            if (indexA < indexB) {
                document.querySelector('.columns').insertBefore(Column.dragged, this.element);
            } else {
                document.querySelector('.columns').insertBefore(Column.dragged, this.element.nextElementSibling);
            }
        } else {
            document.querySelector('.columns').insertBefore(Column.dragged, this.element);
        }
        document.querySelectorAll('.column').forEach(element => element.classList.remove('under'));
    }


}


Column.idCount = 0;
Column.dragged = null;
Column.dropped = null;

