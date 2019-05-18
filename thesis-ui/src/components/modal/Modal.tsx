import * as React from 'react';

interface IModalProps { 
    onCloseClick: () => void;
    onContinueClick: ()=> void;

    title: string;

    show: boolean;
}

export const Modal: React.FunctionComponent<IModalProps> = (props):JSX.Element => {

    if (props.show)
        return (
            <div className='modal-parent'>
                <div className='modal'>
                    <div className='modal-header'>
                        <h3> {props.title} </h3>
                    </div>

                    <div className='modal-body'>
                        {props.children}
                    </div>

                    <div className='modal-buttons'>
                        <button onClick={props.onContinueClick}> Continue </button>
                        <button onClick={props.onCloseClick}> Close </button>
                    </div>
                </div>
            </div>
        );

    return <> </>;
};