import * as React from 'react';

interface ILoginFormProps {
    onLoginClick: (email: string, password: string) => void;
}

interface ILoginFormState {
    email: string;
    password: string;
}

export default class LoginForm extends React.Component<ILoginFormProps, ILoginFormState> {

    public constructor (props: ILoginFormProps) {
        super (props);

        this.state = {
            email: '',
            password: ''
        }
    }

    public render () {
        return (
            <div className='card-container login-form'>
                <h3> Log In </h3>
                <div className='input-container'>
                    <div className='input-area'>
                        <div className='label'> Email: </div>
                        <input type='text' onChange={this.handleEmailChange} />
                    </div>

                    <div className='input-area'>
                        <div className='label'> Password: </div>
                        <input type='password' onChange={this.handlePasswordChange} />
                    </div>

                </div>
                <div className='button-container'>
                    <button onClick={this.handleOnLoginClick} > Login </button>
                </div>
            </div>
        );
    }
    
    private handleEmailChange = (evt: any) => {
        this.setState({email: evt.target.value});
    } 

    private handlePasswordChange = (evt: any) => {
        this.setState({password: evt.target.value});
    } 

    private handleOnLoginClick = () => {
        this.props.onLoginClick(this.state.email, this.state.password);
    }

}