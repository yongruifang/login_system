// import { instance } from "./config";
import { useTokenStore } from 'src/stores/token-store'
import axios from 'axios'
import { globalRouter } from 'src/router/globalRouter'
import { Notify } from 'quasar'

const tokenStore = useTokenStore()
const instance = axios.create({
    baseURL: "http://localhost:8080/api",
    timeout: 1000,
    headers: {
        'Authorization' : process.env.TOKEN_TYPE +  tokenStore.getToken
    }
})
instance.interceptors.request.use(function(config) {
    return config
})

instance.interceptors.response.use(function(res){
    return res
}, function(err)  {
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
    const response = await instance.get("/actor/")
    return response
}

export const editActorApi = async(actor={
    id: "",
    first_name: "",
    last_name: "",
})=>{
    const response = await instance.put("/actor/", actor)
    return response
}
export const deleteActorApi = async(actorId=0) => {
    const response = await instance.get(`/actor/${actorId}`) 
    return response
}
export const addActorApi = async(actor={
    first_name: "",
    last_name: "",
}) => {
    const response = await instance.post("/actor/", actor)
    return response
}