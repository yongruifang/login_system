import axios from 'axios'
import { useTokenStore } from 'src/stores/token-store'
import { globalRouter } from 'src/router/globalRouter'
import { Notify } from 'quasar'


const tokenStore = useTokenStore()
const instance = axios.create({
    baseURL: process.env.BASE_URL,
    timeout: 1000,
    headers: {
            'Authorization' : process.env.TOKEN_TYPE +  tokenStore.getToken
    }
})

instance.interceptors.response.use((res) => {
    console.log('@TODO: redirect to login page', res)
   return res 
}, function(err) {

    console.log("@TODO: redirect to Login Page", err)
    if(err.response.status == 403) {
        console.log('clear token')
        tokenStore.clearToken()
        Notify.create({
            message: 'Token expired, redirect to login page.', 
            color: "negative"
        })
        // 重定向 
        globalRouter.router.push("/")
    }
    return Promise.reject(err)
})

export {instance} 