// import { instance } from "./config";
import { useTokenStore } from 'src/stores/token-store'
import axios from 'axios'
import { globalRouter } from 'src/router/globalRouter'
import { Notify } from 'quasar'

const tokenStore = useTokenStore()
const instance = axios.create({
    baseURL: "http://localhost:8080",
    timeout: 1000,
    headers: {
        'Authorization' : process.env.TOKEN_TYPE +  tokenStore.getToken
    }
})
instance.interceptors.request.use(function(config) {
    console.log('@TODO: actor的axios带了什么token', config)
    return config
})

instance.interceptors.response.use(function(res){
    return res
}, function(err)  {
    console.log("@TODO: redirect to Login Page", err)
    if(err.response.status == 403) {
        console.log('clear token')
        // tokenStore.clearToken()
        Notify.create({
            message: 'Token expired, redirect to login page.', 
            color: "negative"
        })
        // 重定向 
        globalRouter.router.push("/")
    }
    return Promise.reject(err)
})
export const fetchActorApi = async()=>{
    const response = await instance.get("/actor")
    return response
}