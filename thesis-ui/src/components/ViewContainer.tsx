import React, { Component } from 'react'
import { Header } from './header/Header';
import { logout, User, loggedIn, getLoggedInUser } from '../service/client';
import { withRouter } from 'react-router';
import { Routes } from '../service/routes';

interface IViewContainerState {
    user?: User;
}

class ViewContainer extends Component<any, IViewContainerState> {

    public constructor (props: any) {
        super (props);

        this.state = {
            user: props.user 
        };
    }

    componentDidMount () {
        if (loggedIn()) {
            getLoggedInUser().then((user) => {
                this.setState({user: user});
            });
        }
    }

    render() {
        return (
            <div className='view-container'>
                <Header userDisplayName={this.state.user && this.state.user.userDetails ? this.state.user.userDetails.firstName + ' ' + this.state.user.userDetails.lastName : undefined} onLoginClick={this.handleLogin} onLogoutClick={this.handleLogout}/>
                {this.props.children}
            </div>
        )
    }

    private handleLogin = () => {
        this.props.history.push(Routes.login);
    }

    private handleLogout = () => {
        logout ();
        this.setState({user: undefined}, () => {
            this.props.history.push(Routes.login);
        } );
    }
}

export default  withRouter (ViewContainer);
