import React from 'react';
import './App.css';
import Home from './view/Home';
import LoginView from './view/LoginView';
import Cart from './view/Cart';
import User from './view/User';
import Order from './view/Order';
import OrderAdmin from './view/OrderAdmin';
import BookAdmin from './view/BookAdmin';
import UserAdmin from './view/UserAdmin';
import AdminView from './view/AdminView';
import { BookDetail } from './view/BookDetail';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Register from './view/Register';
import { Layout, theme, Menu } from 'antd';
import StaticView from './view/StaticView';

function App() {

    const [loggedIn, setLoggedIn] = React.useState(false);

    return (
        <Router>
        <Routes>
            <Route path="/" element={loggedIn ? <Home /> : <LoginView onLoginSuccess={() => setLoggedIn(true)} />} />
            <Route path="/Home" element={<Home />} />
            <Route path="/Cart" element={<Cart />} />
            <Route path="/MyProfile" element={<User />} />
            <Route path="/MyOrders" element={<Order />} />
            <Route path="/bookDetails" element={<BookDetail/>} />
            <Route path="/register" element={<Register />} />
            <Route path="/admin/orders" element={<OrderAdmin />} />
            <Route path="/admin/books" element={<BookAdmin />} />
            <Route path="/admin/users" element={<UserAdmin />} />
            <Route path="/admin/statistics" element={<AdminView />} />
            <Route path="/statistics" element={<StaticView />} />
        </Routes>
        </Router>
    );
}

export default App;
