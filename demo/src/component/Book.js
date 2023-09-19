import React from 'react';
import { Card } from 'antd';
import {Link} from 'react-router-dom'
import { useHistory,useLocation } from 'react-router-dom';

const { Meta } = Card;


export class Book extends React.Component {

    constructor(props) {
        super(props);
    }  

    render() {
        const {info} = this.props;
        console.log(info)
        
        return (
            <Link to="/bookDetails" state={{info: info}}>
            <Card
                id='card'
                hoverable
                style={{width: 200, padding: 0, height:360}}
                cover={<img alt="image" src={info.image} className={"bookImg"} style={{height:270}}/> }
                //onClick={this.showBookDetails.bind(this, info.bookId)}
            >
                <Meta title={info.title} description={'￥' + info.price}/>
            </Card>
            </Link>
        );
        }           
}

export default Book;