import React from 'react';
import {IdcardOutlined, ShoppingCartOutlined, HomeOutlined, UnorderedListOutlined } from '@ant-design/icons';
import { Breadcrumb, Layout, Menu, Space, theme } from 'antd';
import { useNavigate } from 'react-router';

const items = [
    { 
        key:'Home', 
        icon: <HomeOutlined />,
        children: null,
        label: 'Home',
        path: '/Home',
    },
    {
        key:'MyCart',
        icon: <ShoppingCartOutlined />,
        children: null,
        label: 'My Cart',
        path: '/MyCart',
    },
    {
        key:'MyOrders',
        icon: <UnorderedListOutlined />,
        children: null,
        label: 'My Orders',
        path: '/MyOrders',
    },
    {
        key:'MyProfile',
        icon: <IdcardOutlined />,
        children: null,
        label: 'My Profile',
        path: '/MyProfile',
    },
]

const SideBar = () =>
{
    const navigate = useNavigate();
    const onClick = (e) => {
        navigate(e.key, {replace: true})
    }

    return(
        <Menu id="menu" onClick={onClick} style={{width: 256}}  items={items}/>
    );

}

export default SideBar;
