// 引入 InputNumber 组件
import { useState, useEffect } from 'react';
import { Space, Table, Tag ,Image, Button, List} from 'antd';
import {booksOnCart} from "../localStorage";
import { Layout, theme, Menu } from 'antd';
import {IdcardOutlined, ShoppingCartOutlined, HomeOutlined, UnorderedListOutlined,LineChartOutlined } from '@ant-design/icons';
import {HeaderInfo} from "../component/HeaderInfo";
import { useNavigate } from 'react-router-dom';
import { InputNumber } from 'antd';
import {Modal} from "antd";

const { Column } = Table;

const { Header, Content, Sider } = Layout;

const items =  [
    { 
        key:'Home', 
        icon: <HomeOutlined />,
        children: null,
        label: '首页',
        path: '/Home',
    },
    {
        key:'MyCart',
        icon: <ShoppingCartOutlined />,
        children: null,
        label: '购物车',
        path: '/Cart',
    },
    {
        key:'MyOrders',
        icon: <UnorderedListOutlined />,
        children: null,
        label: '全部订单',
        path: '/MyOrders',
    },
    {
        key:'MyProfile',
        icon: <IdcardOutlined />,
        children: null,
        label: '个人中心',
        path: '/MyProfile',
    },
    {
        key:'Statistics',
        icon: <LineChartOutlined />,
        children: null,
        label: '统计',
        path: '/statistics',
    },
]

const Cart = () => {

    const navigate = useNavigate();

    const handleMenuClick = (e) => {
        navigate(items.find(item => item.key === e.key).path);
    }

    // 声明存储购买数量的状态变量
    const [bookQuantities, setBookQuantities] = useState([]);
    useEffect(() => {
        const initQuantities = booksOnCart.map(book => ({
          id: book.id,
          quantity: 1,
        }));
        setBookQuantities(initQuantities);
      }, []);

    const sendOrder = () => {
        if(!localStorage.getItem('mode')){
            alert('请先登录');
            return;
        }
        Modal.confirm({
            title: '确认订单',
            content: (
                <div>
                    <p>您将购买如下书籍：</p>
                    <List >
                        {
                        booksOnCart.map((item) => (
                            <List.Item>
                                <List.Item.Meta
                                    title={item.title}
                                    description={item.description}
                                />
                            </List.Item>
                        ))}
                    </List>
                </div>
            ),
            onOk() {
                const data = booksOnCart?.map(item => ({
                    bid: item.bid.toString(),
                    title: item.title,
                    author: item.author,
                    price: item.price,
                    isbn: item.isbn,
                    quantity: bookQuantities.find(q => q.id === item.id).quantity.toString(),
                }));
                const uid = localStorage.getItem("uid");
                fetch('http://localhost:8080/order', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                        'Accept': 'application/json'
                        },
                        body: JSON.stringify({
                            'uid': uid,
                            'book': data
                        })
                    })
                    .then(response => {
                        if(response.ok)
                            return response.json();
                    })
                    .then((data) => {
                        Modal.success({
                            title: '成功',
                            content: '订单提交成功！',
                        });
                        booksOnCart.splice(0, booksOnCart.length);
                    })
                    .catch((error) => {
                        console.error(error);
                        Modal.error({
                            title: '错误',
                            content: '订单提交失败！',
                        });
                    })
            },
            onCancel() {},
        });
    }
    

    return(
        <Layout style={{ minHeight: '100vh' }}>
            <Sider>
                <div
                style={{
                    height: 32,
                    margin: 16,
                    background: 'rgba(255, 255, 255, 0.2)',
                }}
                />
                <Menu theme="dark" defaultSelectedKeys={['1']} mode="inline" items={items} onClick={handleMenuClick} />
            </Sider>
            <Layout>
                <Header style={{ padding: 0 }} theme="dark">
                    <HeaderInfo />
                </Header>
                <Content style={{ padding: 10 }} className='home-content'>
                <div className="Shopping">
                    <h1>My Orders</h1>
                    <Table dataSource={booksOnCart} pagination={false}>
                        {/* 增加一个 Column 组件，标题为“数量”，内容为 InputNumber 组件 */}
                        <Column
                            title="数量"
                            key="quantity"
                            render={(text, record) => {
                                const idx = bookQuantities.findIndex(item => item.id === record.id);
                                // 如果 bookQuantities 不存在或者找不到对应的书籍对象，则返回默认的数量值 1
                                const quantity = idx >= 0 ? bookQuantities[idx]?.quantity ?? 1 : 1;
                                return (
                                <InputNumber
                                    min={1}
                                    defaultValue={1}
                                    value={quantity}
                                    onChange={(value) => {
                                    const newQuantities = [...bookQuantities];
                                    // 如果找不到对应的书籍对象，则创建一个新的对象并添加到数组中
                                    if (idx < 0) {
                                        newQuantities.push({ id: record.id, quantity: value });
                                    } else {
                                        newQuantities[idx] = { ...newQuantities[idx], quantity: value };
                                    }
                                    setBookQuantities(newQuantities);
                                    }}
                                />
                                );
                            }}
                            />
                        <Column title="标题"  dataIndex="title"/>
                        <Column title="作者" dataIndex="author" />
                        <Column title="价格"  dataIndex="price"/>
                        <Column title="ISBN"  dataIndex="isbn"/>
                        
                    </Table>

                    <Button type="primary" size="large" style={{float: 'right', margin: '10px'}}
                        onClick={() => {sendOrder()}}
                    >
                        购买
                    </Button>
                </div>
                </Content>
            </Layout>
        </Layout>
    )
}

export default Cart;
