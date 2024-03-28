import { defineStore } from 'pinia'

export const useTokenStore = defineStore('token', {
    state: () => ({
        token: "", 
    }), 
    getters: {
        getToken: (state) => state.token,
    },
    actions: {
        setToken(token) {
            this.token = token
        },
        clearToken(token) {
            this.token = ""
        }
    }
})