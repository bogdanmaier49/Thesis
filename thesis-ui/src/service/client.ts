
import axios from 'axios';

export const BASE_LINK = 'http://localhost:9090';
export const USERS_LINK = '/users';
export const LOGIN_LINK = '/login';
export const PRODUCERS_LINK = '/producer';
export const COUNTRY_LINK = '/country';

export const LOCAL_STORAGE_TOKEN_KEY = 'token';

export interface UserDetails {
    id?: number;
    firstName?: string;
    lastName?: string;
    phone?: string;
}

export interface User {
    id?: string;
    email?: string;
    password?: string;
    addedDate?: string;
    userDetails?: UserDetails;
}

export interface Producer {
    id?: number;
    name?: string;
    country?: Country;
}

export interface Country { 
    id?: number;
    name?: string;
    code?: string;  
}

export const loggedIn = (): boolean => {
    if (localStorage.getItem(LOCAL_STORAGE_TOKEN_KEY)) {
        return true;
    }

    return false;
}

export const logout = () => {
    if (localStorage.getItem(LOCAL_STORAGE_TOKEN_KEY)) {
        localStorage.removeItem(LOCAL_STORAGE_TOKEN_KEY);
    }
}

export const getLoggedInUser = () => {

    return new Promise((resolve, reject) => {
        let token: string | null = localStorage.getItem(LOCAL_STORAGE_TOKEN_KEY);

        if (token) {
            return GET_loggedInUser(token).then((res) => {
                resolve(res.data);
            }); 
        }

        reject('Failed to get logged in user');
    });

}

export const POST_User = (user: User) => {
    return axios.post(BASE_LINK + USERS_LINK, user);
}

export const POST_Login = (email: string, password: string) => {
    let requestData = {
        "email": email,
        "password": password
    };

    return axios.post(BASE_LINK + LOGIN_LINK, requestData);
}

export const GET_loggedInUser = (token: string) => {
    let headers = {
        Authorization: token
    };
    return axios.get(BASE_LINK + USERS_LINK + '/loggedin', {
        headers: headers
    });
}

export const GET_Producers = (token: string) => {
    let headers = {
        Authorization: token
    };
    return axios.get(BASE_LINK + USERS_LINK + PRODUCERS_LINK, {
        headers: headers
    });
}

export const doGET = (link: string, token?: string) => {
    let headers = {
        Authorization: token
    };
    return axios.get(BASE_LINK + link, {
        headers: headers
    });
}

export const doPOST = (link: string, data: any, token?: string) => {
    let headers = {
        Authorization: token
    };
    return axios.post(BASE_LINK + link, data, {
        headers: headers
    });
}

export const doDELETE = (link: string, token?: string) => {
    let headers = {
        Authorization: token
    };
    return axios.delete(BASE_LINK + link, {
        headers: headers
    });
}

export const doPATCH = (link: string, data: any, token?: string) => {
    let headers = {
        Authorization: token
    };
    return axios.patch(BASE_LINK + link, data, {
        headers: headers
    });
}