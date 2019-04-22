import React, { Component } from 'react';
import './App.scss';
import { BrowserRouter, Route } from 'react-router-dom';
import LoginView from './views/loginView/LoginView';
import { loggedIn } from './service/client';
import MaintainerView from './views/maintainer/MaintainerView';
import { Routes } from './service/routes';

class App extends Component {

	render() {
		return (
			<BrowserRouter>
				<Route exact path={Routes.home} component={LoginView} />
				<Route path={Routes.login} component={LoginView} />
				<Route path={Routes.maintainer} component={MaintainerView} onEnter={this.requireAuth}/>
			</BrowserRouter>
		);
	}

	private requireAuth = (nextState: any, replace: any) => {
		if (! loggedIn()) {
			replace({
				pathname: Routes.login
			})
		}
	}
}

export default App;
