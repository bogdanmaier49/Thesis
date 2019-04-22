import * as React from 'react';
import { loggedIn } from '../../service/client';

interface IHeaderProps {
    onLogoutClick: () => void;
    onLoginClick: () => void;

    userDisplayName?: string;
    onClickUserDisplayName?: () => void;
}

export const Header = (props: IHeaderProps): JSX.Element => (

    <header>

        <div className='section left-buttons'>
            <button> Button 1 </button>
            <button> Button 2 </button>
            <button> Button 3 </button>
        </div>

        <div className='section logo'>
            Product Maintainer
        </div>

        <div className='section right-buttons'>
            {
                loggedIn() ? 
                        <>
                        <button> {props.userDisplayName} </button>
                        <span> / </span>
                        <button onClick={props.onLogoutClick}> Log Out </button>
                        </>
                    :
                        <button onClick={props.onLoginClick}> Log In </button>
            }
        </div>

    </header>

);

