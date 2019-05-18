import * as React from 'react';
import ReactTable from 'react-table';
import { Producer } from '../../service/client';

interface IProducersTableProps {
    data: Producer[];

    onRemoveClick: (producer: Producer) => void;
    onUpdateClick: (producer: Producer) => void;
}

interface IProducersTableState {

}

export default class ProducersTable extends React.Component<IProducersTableProps, IProducersTableState> {

    public constructor (props: IProducersTableProps) {
        super (props);
    }

    public render () {

        const columns = [
            {
                Header: 'Id',
                accessor: 'id'
            },
            {
                Header: 'Name',
                accessor: 'name'
            },
            {
                id: 'countryCode',
                Header: 'Country',
                accessor: (d: any) => d.country.code
            },
            {
                accessor: 'actions',
                Header: 'Actions',
                Cell: (props: any) => (
                    <div>
                        <a onClick={() => {this.props.onUpdateClick(props.original)}}> Update </a>
                        <span> > </span> 
                        <a onClick={() => {this.props.onRemoveClick(props.original)}}> Delete </a>
                    </div>
                )   
            }
        ];
        
        return <ReactTable
            data={this.props.data}
            columns={columns}

            // getTdProps={(state: any, rowInfo: any, column: any, instance: any) => {
            //     return {
            //         onClick: (e: any) => {
            //             console.log('It was in this row:', rowInfo)
            //         }
            //     }
            // }}
        />
    }
}