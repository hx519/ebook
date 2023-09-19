// import React from 'react';
// import {Button, List, Modal} from 'antd'
// import {Book} from './Book'
// // import {getBooks} from '../service/BookService'
// import { booksOnCart } from '../localStorage';



// function addBook() {
//     if(!localStorage.getItem('mode')){
//         alert('请先登录');
//         return;
//     }
//     if(localStorage.getItem('mode') === 'user'){
//         alert('您没有权限新增书籍');
//         return;
//     }
//     // 等待用户填入文本信息
//     Modal.confirm({
//         title: '新增书籍',
//         content:<div>
//                     <div>书名：<input id='bookName' type='text'/></div>
//                     <div>作者：<input id='bookAuthor' type='text'/></div>
//                     <div>价格：<input id='bookPrice' type='text'/></div>
//                     <div>库存：<input id='bookStock' type='text'/></div>
//                     <div>图片：<input id='bookImage' type='text'/></div>
//                     <div>描述：<input id='bookDescription' type='text'/></div>
//                     <div>分类：<input id='bookCategory' tyLpe='text'/></div>
//                     <div>ISBN：<input id='bookLabel' type='text'/></div>
//                 </div>,
//         onOk: () => {
//             // 从输入框中获取信息
//             let bookName = document.getElementById('bookName').value;
//             let bookAuthor = document.getElementById('bookAuthor').value;
//             let bookPrice = document.getElementById('bookPrice').value;
//             let bookStock = document.getElementById('bookStock').value;
//             let bookImage = document.getElementById('bookImage').value;
//             let bookDescription = document.getElementById('bookDescription').value;
//             let bookCategory = document.getElementById('bookCategory').value;
//             let bookLabel = document.getElementById('bookLabel').value;
//             // 将信息封装成对象
//             let book = {
//                 title: bookName,
//                 author: bookAuthor,
//                 type: bookCategory,
//                 price: bookPrice,
//                 inventory: bookStock,
//                 description: bookDescription,
//                 isbn: bookLabel,
//                 image: bookImage
//             };
//             // 将对象转换成json字符串
//             let bookJson = JSON.stringify(book);
//             console.log(bookJson);
//             // 发送请求
//             fetch('http://localhost:8080/addBook', {
//                 method: 'POST',
//                 headers: {
//                     'Content-Type': 'application/json'
//                 },
//                 body: bookJson
//             })

//         }   
// })
// }

// export class BookList extends React.Component {
    
//     constructor(props) {
//         super(props);
//         this.state = {books: [] };
//     }

//     componentDidMount() {
//         fetch('http://localhost:8080/bookList')
//             .then(response => response.json())
//             .then((data) => {
//                 this.setState({ books: data})
//             })
//             .catch(console.log("error"))
//     }

//     render() {
//         // const {books} = this.props;

//         if(localStorage.getItem('mode') === 'admin'){
//             return (
//                 <div>
//                     <List id='bookList'
//                         grid={{gutter: 10, column: 4}}
//                         dataSource={this.state.books}
//                         // dataSource={books}
//                         pagination={false}

//                         renderItem={item => (
//                             <List.Item >
//                                 <Book info={item} />
//                             </List.Item>
//                         )}
//                     />
//                     <Button onClick={addBook}
//                         style={{width: '100%', height: '100px', fontSize: '30px', marginTop: '20px'}}   
//                     >
//                         新增书籍
//                     </Button>
//                 </div>
//             );
//         }

//         return (
//             <div>
//                 <List id='bookList'
//                     grid={{gutter: 10, column: 4}}
//                     dataSource={this.state.books}
//                     // dataSource={books}
//                     pagination={false}

//                     renderItem={item => (
//                         <List.Item >
//                             <Book info={item} />
//                         </List.Item>
//                     )}
//                 />
//                 {/* <Button onClick={addBook}
//                     style={{width: '100%', height: '100px', fontSize: '30px', marginTop: '20px'}}
//                     visible={localStorage.getItem('mode') === 'admin'}
//                 >
//                     新增书籍
//                 </Button> */}
//             </div>
//         );
//     }

// }


import React from 'react';
import {Button, Input, List, Modal} from 'antd'
import {Book} from './Book'
import { booksOnCart } from '../localStorage';

function addBook() {
    if(!localStorage.getItem('mode')){
        alert('请先登录');
        return;
    }
    if(localStorage.getItem('mode') === 'user'){
        alert('您没有权限新增书籍');
        return;
    }
    // 等待用户填入文本信息
    Modal.confirm({
        title: '新增书籍',
        content:<div>
                    <div>书名：<input id='bookName' type='text'/></div>
                    <div>作者：<input id='bookAuthor' type='text'/></div>
                    <div>价格：<input id='bookPrice' type='text'/></div>
                    <div>库存：<input id='bookStock' type='text'/></div>
                    <div>图片：<input id='bookImage' type='text'/></div>
                    <div>描述：<input id='bookDescription' type='text'/></div>
                    <div>分类：<input id='bookCategory' tyLpe='text'/></div>
                    <div>ISBN：<input id='bookLabel' type='text'/></div>
                </div>,
        onOk: () => {
            // 从输入框中获取信息
            let bookName = document.getElementById('bookName').value;
            let bookAuthor = document.getElementById('bookAuthor').value;
            let bookPrice = document.getElementById('bookPrice').value;
            let bookStock = document.getElementById('bookStock').value;
            let bookImage = document.getElementById('bookImage').value;
            let bookDescription = document.getElementById('bookDescription').value;
            let bookCategory = document.getElementById('bookCategory').value;
            let bookLabel = document.getElementById('bookLabel').value;
            // 将信息封装成对象
            let book = {
                title: bookName,
                author: bookAuthor,
                type: bookCategory,
                price: bookPrice,
                inventory: bookStock,
                description: bookDescription,
                isbn: bookLabel,
                image: bookImage
            };
            // 将对象转换成json字符串
            let bookJson = JSON.stringify(book);
            console.log(bookJson);
            // 发送请求
            fetch('http://localhost:8080/addBook', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: bookJson
            })
            window.location.reload();
        }   
})
}

export class BookList extends React.Component {
    
    constructor(props) {
        super(props);
        this.state = {books: [], searchValue: '' };
    }

    componentDidMount() {
        fetch('http://localhost:8080/bookList')
            .then(response => response.json())
            .then((data) => {
                this.setState({ books: data})
            })
            .catch(console.log("error"))
    }

    handleSearch = (event) => {
        this.setState({searchValue: event.target.value})
    }

    render() {
        const filteredBooks = this.state.books.filter(book => book.title.includes(this.state.searchValue))

        if(localStorage.getItem('mode') === 'admin'){
            return (
                <div>
                    <Input placeholder="搜索书名" style={{marginBottom: '20px'}} onChange={this.handleSearch}/>
                    <List id='bookList'
                        grid={{gutter: 10, column: 4}}
                        dataSource={filteredBooks}
                        pagination={false}
                        renderItem={item => (
                            <List.Item >
                                <Book info={item} />
                            </List.Item>
                        )}
                    />
                    <Button onClick={addBook}
                        style={{width: '100%', height: '100px', fontSize: '30px', marginTop: '20px'}}   
                    >
                        新增书籍
                    </Button>
                </div>
            );
        }

        return (
            <div>
                <Input placeholder="搜索书名" style={{marginBottom: '20px'}} onChange={this.handleSearch}/>
                <List id='bookList'
                    grid={{gutter: 10, column: 4}}
                    dataSource={filteredBooks}
                    pagination={false}

                    renderItem={item => (
                        <List.Item >
                            <Book info={item} />
                        </List.Item>
                    )}
                />
            </div>
        );
    }

}