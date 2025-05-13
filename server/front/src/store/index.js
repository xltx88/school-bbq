import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        token: localStorage.getItem("token"),
        userInfo: {},
        username:''
    },
    mutations: {
        // set
        SET_TOKEN: (state, token) => {
            state.token = token
            localStorage.setItem("token", token)
        },
        SET_USERINFO: (state, userInfo) => {
            state.userInfo = userInfo
            // 同时更新localStorage以持久化存储
            localStorage.setItem('userinfo', JSON.stringify(userInfo))
        },
        SET_USERNAME: (state, username) => {
            state.username = username
            localStorage.setItem('username', username)
        },
        REMOVE_INFO: (state) => {
            state.token = ''
            state.userInfo = {}
            state.username = ''
            localStorage.setItem("token", '')
            localStorage.removeItem('userinfo')
            localStorage.removeItem('username')
        }
    },
    getters: {
        // get
        getUser: state => {
            return state.userInfo
        },
        get_token: (state) => {
            return state.token
        }
    },
    actions: {
    },
    modules: {
    }
})