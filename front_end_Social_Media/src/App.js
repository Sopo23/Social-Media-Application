import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Login from './Login/Login';
import Register from './Register/Register';
import Profile from './Profile/Profile';
import PostsPage from './PostsPage/PostsPage';
import './App.css';

function App() {
    return (
        <Router>
            <div className="App">
                <Routes>
                    <Route path="/login" element={<Login />} />
                    <Route path="/register" element={<Register />} />
                    <Route path="/profile" element={<Profile />} />
                    <Route path="/posts" element={<PostsPage />} />
                    <Route path="/" element={<div>Welcome to the Home Page</div>} />
                </Routes>
            </div>
        </Router>
    );
}

export default App;
