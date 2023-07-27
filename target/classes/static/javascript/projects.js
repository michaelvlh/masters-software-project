/*
 * @Author: Jipu Li 
 * @Date: 2022-03-20 21:32:20 
 * @Last Modified by: Jipu Li
 * @Last Modified time: 2022-05-10 00:07:27
 */



var app = new Vue({
    el: '#app',
    data() {
        return {
            title: 'Food For All',
            loginState: loginState,
            term: '',
            projects: projects,
            filteredProjects: projects,
        }
    },
    methods: {},
    watch: {
        term: function (term) {
            if (term == '') {
                this.filteredProjects = this.projects
                return
            }
            var sterm = term.toLowerCase()
            var fproject = []
            this.projects.forEach(project => {
                console.log(sterm, project.title.toLowerCase())
                if (project.title.toLowerCase().indexOf(sterm) != -1)
                    fproject.push(project)
            });
            this.filteredProjects = fproject
        }
    }

})