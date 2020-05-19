class Project {

    constructor(id = null, projectName = null, userID = null) {

        const instance = this;

        const projectElement = this.element = document.createElement('div');
        projectElement.classList.add('project');
        const project_link = document.createElement('a');
        const title = document.createElement('div');
        title.classList.add('title');
        const project_title = document.createElement('div');
        project_title.classList.add('project-title');
        project_title.innerText = projectName;
        const project_remove = document.createElement('span');
        project_remove.id = 'project-remove';
        project_remove.innerHTML = '<i class="fa fa-window-close" aria-hidden="true"></i>';
        const project_img = document.createElement('div');
        project_img.classList.add('project-img');
        project_img.innerHTML = '<i class="fa fa-briefcase" aria-hidden="true"></i>';

        title.append(project_title, project_remove);
        project_link.append(title, project_img);
        project_link.href = '/projects/' + userID + '/' + id;
        projectElement.append(project_link);
        document.querySelector('.projects').append(projectElement);

        this.remove(project_remove, id, projectElement);

    }

    remove(removeElement = null, id = null, element = null) {
        removeElement.addEventListener('click', function (event) {
            $.ajax({
                type: "POST",
                global: false,
                // contentType: "application/json",
                url: '/projects/remove',
                data: {projectId: id},
                dataType: 'text',
                timeout: 600000,
                // contentType: "application/json; charset=utf-8",
                // processData: false, // для передачи картинки(файла) нужно false
                success: function (data, status) { // в случае успешного завершения
                    console.log(status, "SUCCESS : ", data);
                    element.remove();
                },
                error: function (data, status) { // в случае провала
                    console.log(status, "ERROR : ", data);
                }
            });
        });
    }

}

function loadProjects(id, name, ID) {
    const project = new Project(id, name, ID);
}