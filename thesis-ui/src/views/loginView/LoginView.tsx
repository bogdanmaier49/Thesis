import React, { Component } from 'react';
import { withRouter } from 'react-router';
import ViewContainer from '../../components/ViewContainer';
import LoginForm from '../../components/loginForm/LoginForm';
import { POST_Login, LOCAL_STORAGE_TOKEN_KEY, User, GET_loggedInUser } from '../../service/client';
import { Routes } from '../../service/routes';

interface LoginViewState { 
    user?: User;
}

class LoginView extends Component<any, LoginViewState> {

    public constructor (props: any) {
        super (props);

        this.state = {
            user: props.user 
        };
    }

    render() {
        return (
            <ViewContainer user={this.state.user}>

                <div className='login-view-container'>
                    <LoginForm onLoginClick={this.handleLogin} />
                </div>

            </ViewContainer>
        );
    }

    private handleLogin = (email: string, password: string) => {
        POST_Login (email, password).then((res)=>{
            if (res && res.data && res.data.Authorization) {
                localStorage.setItem(LOCAL_STORAGE_TOKEN_KEY, res.data.Authorization)
                GET_loggedInUser(res.data.Authorization).then(
                    (res) => {
                        this.setState({user: res.data}, () => {
                            this.props.history.push(Routes.maintainer);
                        });
                    }
                );
            }
        });
    }
}

export default withRouter (LoginView);
