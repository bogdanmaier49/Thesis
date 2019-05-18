import * as React from 'react';
import { withRouter } from 'react-router';
import ViewContainer from '../../components/ViewContainer';
import ProducersTable from '../../components/table/ProducersTable';
import { Producer, doGET, LOCAL_STORAGE_TOKEN_KEY, PRODUCERS_LINK, Country, COUNTRY_LINK, doPOST, doDELETE } from '../../service/client';
import { Modal } from '../../components/modal/Modal';
import { Loader } from '../../components/loader/Loader';
import Select from 'react-select';
import AddProducerModal from './AddProducerModal';

interface IProducersViewState {
    loading: boolean;

    producers?: Producer[];
    countries?: Country[];

    showAddProducerModal: boolean;

    showUpdateProducerModal: boolean;
    producerToUpdate?: Producer;

    deleteCheckModal: boolean;
    producerToDelete?: Producer;
}

class ProducersView extends React.Component <any, IProducersViewState> {

    public constructor (props: any) {
        super(props);

        this.state = {
            loading: true,
            showAddProducerModal: false,
            showUpdateProducerModal: false,
            deleteCheckModal: false
        };
    }

    componentDidMount () {
        this.requestData().then(() => {console.log(this.state); this.setState({loading: false})});
    }

    public render () {
        return (
            <>

                <AddProducerModal 
                    onCloseClick = {() => {this.setState({showAddProducerModal: false})}}
                    onSubmitClick ={this.handleAddProducer}
                    show={this.state.showAddProducerModal}
                    countries={this.state.countries}
                />

                <AddProducerModal 
                    onCloseClick = {() => {this.setState({showUpdateProducerModal: false})}}
                    onSubmitClick ={(producer: Producer) => {console.log(producer)} }
                    show={this.state.showUpdateProducerModal}
                    countries={this.state.countries}
                    producer={this.state.producerToUpdate}
                />

                <Modal 
                    onCloseClick={() => {this.setState({deleteCheckModal: false})}} 
                    onContinueClick={() => {
                        this.setState({loading: true}, () => {
                            if (this.state.producerToDelete)
                                this.handleProducerRemove(this.state.producerToDelete).then(() => {this.setState({loading: false, producerToDelete: undefined, deleteCheckModal: false})});
                            else 
                                this.setState({loading: false});
                        });
                    }}
                    title={'Remove Producer'}
                    show={this.state.deleteCheckModal && this.state.producerToDelete !== undefined}
                >
                    Are you sure you want to delete producer: 
                    <span> {this.state.producerToDelete ? this.state.producerToDelete.name : 'undeifned'} </span>
                </Modal>

                <ViewContainer>
                    Producers View

                    {!this.state.loading ?
                        <div className='table-view'> 
                            <div className='table-view-section'>
                                <ProducersTable 
                                    data={this.state.producers ? this.state.producers : []} 
                                    onRemoveClick={(producer: Producer) => {
                                        this.setState({
                                            producerToDelete: producer,
                                            deleteCheckModal: true
                                        })
                                    }}
                                    onUpdateClick={(producer: Producer) => {
                                        this.setState({
                                            showAddProducerModal: true,
                                            producerToUpdate: producer
                                        });
                                    }}
                                />
                            </div>
                            <div className='table-view-section'>
                                <h3> Actions </h3>
                                <button onClick={this.onAddProducerClick}> Add New Producer </button>
                            </div>
                        </div>
                        : <Loader />
                    }
                </ViewContainer>
            </>
        );
    }

    private onAddProducerClick = () => {
        this.setState({showAddProducerModal: true});
    }

    private handleAddProducer = (producer: Producer) => {
        this.setState({loading: true, showAddProducerModal: false}, () => {
            let token: string | null = localStorage.getItem(LOCAL_STORAGE_TOKEN_KEY);
            if (token) {
                doPOST(PRODUCERS_LINK, producer, token).then(
                    (res: any) => {
                        console.log('Producer added successfully');
                        this.requestProducers().then(() => {this.setState({loading: false})});
                    }
                ).catch(
                    (err: any) => {
                        console.log('Failed to add producer');
                    }
                );
            }
        });
    }

    private async requestData () {
        await this.requestProducers();
        await this.requestCountries();
    }

    private async requestProducers () {
        let token: string | null = localStorage.getItem(LOCAL_STORAGE_TOKEN_KEY);
        if (token) {
            await doGET(PRODUCERS_LINK, token).then((res) => {
                this.setState({producers: res.data});
            });
        }
    }

    private async requestCountries () {
        let token: string | null = localStorage.getItem(LOCAL_STORAGE_TOKEN_KEY);
        if (token) {
            await doGET(COUNTRY_LINK, token).then((res) => {
                this.setState({countries: res.data});
            });
        }
    }

    private async handleProducerRemove (producer: Producer) {
        let token: string | null = localStorage.getItem(LOCAL_STORAGE_TOKEN_KEY);
        if (token) {
            await doDELETE(PRODUCERS_LINK + '/' + producer.id, token).then((res) => {
                this.requestProducers().then(() => {this.setState({loading: false})});
            })
        }
    }

    private async handleProducerUpdate (producer: Producer) {
        console.log('UPDATE PRODUCER', producer);
    }

}

export default withRouter(ProducersView);