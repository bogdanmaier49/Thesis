import React, { Component } from 'react';
import './App.scss';
import { BrowserRouter, Route, Redirect } from 'react-router-dom';
import LoginView from './views/loginView/LoginView';
import { loggedIn } from './service/client';
import MaintainerView from './views/maintainer/MaintainerView';
import { Routes } from './service/routes';
import ProducersView from './views/producersView/ProducersView';

class App extends Component {

	render() {
		return (
			<BrowserRouter>
				<Route exact path={Routes.home} render={() => (
					<h1> Welcome ! </h1>
				)} />
				<Route path={Routes.login} render={()=>(
					<LoginView />
				)} />

				<Route path={Routes.maintainer} render={() => {
					if (! loggedIn() ) {
						console.log();
						return <Redirect to={Routes.login} />
					} else {
						return <MaintainerView />
					}
				}}/>

				<Route path={Routes.producers} render={() => {
					if (! loggedIn() ) {
						return <Redirect to={Routes.login} />
					} else {
						return <ProducersView />
					}
				}}/>

			</BrowserRouter>
		);
	}

}

export default App;
