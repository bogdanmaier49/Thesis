import * as React from 'react';
import { loggedIn } from '../../service/client';

export interface IHeaderMenuButton {
    label: string;
    onClick: () => void;
}

interface IHeaderProps {
    onLogoutClick: () => void;
    onLoginClick: () => void;

    userDisplayName?: string;
    onClickUserDisplayName?: () => void;

    buttons: IHeaderMenuButton[];

    handleMenu?: () => any;
}

export const Header = (props: IHeaderProps): JSX.Element => (

    <header>

        <div className='section left-buttons'>
            {props.buttons.map((button: IHeaderMenuButton, index: number) => (
                <button key={'menu-button-' + index} onClick={button.onClick}> {button.label} </button>
            ))}
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

