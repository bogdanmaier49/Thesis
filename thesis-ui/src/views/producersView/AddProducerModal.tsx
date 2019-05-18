import * as React from 'react';
import { Modal } from '../../components/modal/Modal';
import Select from 'react-select';
import { Country, Producer } from '../../service/client';

interface IAddProducerModalProps {
    producer?: Producer;
    countries?: Country[];
    show: boolean;
    onCloseClick: () => void;
    onSubmitClick: (producer: Producer) => void;
}

interface IAddProducerModalState {
    producer: Producer;
}

export default class AddProducerModal extends React.Component <IAddProducerModalProps, IAddProducerModalState> {

    public constructor (props: IAddProducerModalProps) {
        super (props);

        this.state = {
            producer: this.props.producer ? this.props.producer : {}
        }
    }

    public render () {
        return (
            <Modal onContinueClick={() => {this.props.onSubmitClick(this.state.producer)}} onCloseClick={this.props.onCloseClick} title='Add Producer' show={this.props.show}>

                <label className='width100'>
                    <span> Name </span>
                    <input type='text' onChange={this.handleNameChenge} />
                </label>

                <label className='width100'>
                    Country
                    <Select
                        options={this.props.countries ? this.props.countries.map((country: Country) => {
                            return {value: country, label: country.name}
                        }) : []}
                        onChange = {this.handleCountryChange}
                        className='wid  th100'
                    />
                </label>

            </Modal>
        );
    }

    private handleNameChenge = (e: any) => {
        let producer: Producer = this.state.producer;
        producer.name = e.target.value;
        this.setState ({producer: producer});
    }

    private handleCountryChange = (option: any) => {
        let producer: Producer = this.state.producer;
        producer.country = option.value;
        this.setState ({producer: producer});
    }

}