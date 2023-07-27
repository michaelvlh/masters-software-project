/*
 * @Author: Jipu Li 
 * @Date: 2022-03-17 15:36:48
 * @Last Modified by: Jipu Li
 * @Last Modified time: 2022-03-29 16:48:17
 */
console.log(session)

var app = new Vue({
    el: '#app',
    data() {
        return {
            title: 'Food For All',
            loginState: session.SPRING_SECURITY_CONTEXT.authentication.authenticated
        }
    },
    methods: {}
})