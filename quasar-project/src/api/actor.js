// import { instance } from "./config";
import { useTokenStore } from 'src/stores/token-store'
import axios from 'axios'

const tokenStore = useTokenStore()
const instance = axios.create({
    baseURL: "http://localhost:8080",
    timeout: 1000,
    headers: {
        'Authorization' : process.env.TOKEN_TYPE +  tokenStore.getToken
    }
})
export const fetchActorApi = async()=>{
    const response = await instance.get("/actor")
    return response
}