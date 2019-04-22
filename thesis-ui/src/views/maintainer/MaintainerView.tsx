import React, { Component } from 'react'
import { withRouter } from 'react-router';
import ViewContainer from '../../components/ViewContainer';

interface IMaintainerViewState {

}

class MaintainerView extends Component<any, IMaintainerViewState> {
    render() {
        return (
            <ViewContainer>
                Maintainer View ...
            </ViewContainer>
        )
    }
}

export default withRouter(MaintainerView);
