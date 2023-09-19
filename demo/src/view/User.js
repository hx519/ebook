import { Button, Form, Input, InputNumber, Upload } from 'antd';
import { PlusOutlined } from '@ant-design/icons';
import { Layout, theme, Menu, Card, Avatar, Image } from 'antd';
import {IdcardOutlined, ShoppingCartOutlined, HomeOutlined, UnorderedListOutlined,LineChartOutlined } from '@ant-design/icons';
import {HeaderInfo} from "../component/HeaderInfo";
import { useNavigate } from 'react-router-dom';

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


const layout = {
    labelCol: {
        span: 8,
    },
    wrapperCol: {
        span: 16,
    },
};

// /* eslint-disable no-template-curly-in-string */
// const validateMessages = {
//     required: '${label} is required!',
//     types: {
//         email: '${label} is not a valid email!',
//         number: '${label} is not a valid number!',
//     },
//     number: {
//         range: '${label} must be between ${min} and ${max}',
//     },
// };
// /* eslint-enable no-template-curly-in-string */

// const onFinish = (values) => {
//     console.log(values);
// };

const User = () => {
    const navigate = useNavigate();

    const handleMenuClick = (e) => {
        navigate(items.find(item => item.key === e.key).path);
    }

    return (
    <div>
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
                    
                <div>
                        <h1>My Profile</h1>
                    <Form >
                        <Form.Item>
                            <Image src={localStorage.getItem("avatar")} size={32} />
                        </Form.Item>
                        <Form.Item
                            name={['user', 'name']}
                            label="Name"
                        >
                            {localStorage.getItem('username')}
                        </Form.Item>
                        <Form.Item
                            name={['user', 'email']}
                            label="Email"
                        >
                            {localStorage.getItem('email')}
                        </Form.Item>
                        <Form.Item
                            name={['user', 'phone']}
                            label="Phone" 
                        >
                            {localStorage.getItem('phone')}
                        </Form.Item>
                        <Form.Item
                            name={['user', 'address']}
                            label="Address"
                        >
                            {localStorage.getItem('address')}
                        </Form.Item>
                        
                    </Form>
                    {/* <Card>
                        <Avatar src={localStorage.getItem("avatar")} size={64} />
                        <Card.Meta
                        title={localStorage.getItem("username")}
                        description={`${localStorage.getItem("address")}, ${localStorage.getItem("phone")}`}
                        />
                        <div>Email: {localStorage.getItem("email")}</div>
                    </Card> */}
                </div>

                </Content>
            </Layout>
        </Layout>
    </div>  
    );
}

export default User;

