import { instance } from "./config";


export const loginApi = async({email, password}) => {
    const response = await instance.post("/auth/login", {
        email: email,
        password: password
    });
    return response;
}