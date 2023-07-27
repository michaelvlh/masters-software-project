/*
 * @Author: Jipu Li 
 * @Date: 2022-03-29 16:57:23 
 * @Last Modified by: Jipu Li
 * @Last Modified time: 2022-03-29 16:59:09
 */

var app = new Vue({
    el: '#app',
    data() {
        return {
            title: 'Food For All',
            loginState: session.SPRING_SECURITY_CONTEXT.authentication.authenticated,
            tagActives: {
                projects: false,
                graphs: false,
                settings: false,
                profiles: true,
            }
        }
    },
    methods: {}
})