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
        },
        REMOVE_INFO: (state) => {
            state.token = ''
            state.userInfo = {}
            localStorage.setItem("token", '')
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