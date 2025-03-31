import React, { useState, useEffect } from 'react';
import ClientService from '../Service/ClientService';
import AuthService from '../Service/AuthService';
import { useNavigate } from 'react-router-dom';
import '../App.css';

const Profile = () => {
    const [user, setUser] = useState({});
    const [followers, setFollowers] = useState([]);
    const [following, setFollowing] = useState([]);
    const [allClients, setAllClients] = useState([]);
    const [showFollowers, setShowFollowers] = useState(false);
    const [showFollowing, setShowFollowing] = useState(false);
    const [showAllClients, setShowAllClients] = useState(false);
    const [showEditBox, setShowEditBox] = useState(false);
    const [newBio, setNewBio] = useState("");
    const [followUsername, setFollowUsername] = useState("");
    const [showFollowBox, setShowFollowBox] = useState(false);
    const [privacyData, setPrivacyData] = useState(null);
    const [showPrivacyBox, setShowPrivacyBox] = useState(false);

    const authService = new AuthService();
    const clientService = new ClientService();
    const navigate = useNavigate();

    useEffect(() => {
        fetchClientData();
        fetchFollowers();
        fetchFollowing();
    }, []);

    const fetchClientData = async () => {
        try {
            const clientId = localStorage.getItem('current_logged_user');
            const response = await clientService.getClientData(clientId);
            setUser(response.user);
        } catch (error) {
            console.error('Error fetching client data', error);
        }
    };

    const fetchFollowers = async () => {
        try {
            const clientId = localStorage.getItem('current_logged_user');
            const response = await clientService.getFollowers(clientId);
            setFollowers(response);
        } catch (error) {
            console.error('Error fetching followers', error);
        }
    };

    const fetchFollowing = async () => {
        try {
            const clientId = localStorage.getItem('current_logged_user');
            const response = await clientService.getFollowing(clientId);
            setFollowing(response);
        } catch (error) {
            console.error('Error fetching following accounts', error);
        }
    };

    const fetchAllClients = async () => {
        try {
            const response = await clientService.getAllClients();
            console.log(response)
            setAllClients(response);
        } catch (error) {
            console.error('Error fetching all clients', error);
        }
    };

    const handleUpdate = async () => {
        try {
            const clientId = localStorage.getItem('current_logged_user');
            await clientService.updateClientData(clientId, newBio);
            alert('Profile updated successfully');
            fetchClientData(); // Refresh user details
            setShowEditBox(false); // Hide the edit box
        } catch (error) {
            console.error('Error updating profile', error);
        }
    };

    const handleDelete = async () => {
        try {
            const clientId = localStorage.getItem('current_logged_user');
            await clientService.deleteClient(clientId);
            alert('Account deleted successfully');
            navigate('/register');
        } catch (error) {
            console.error('Error deleting account', error);
        }
    };

    const handlePrivacy = async () => {
        try {
            const clientId = localStorage.getItem('current_logged_user');
            const response = await authService.viewUserPrivacy(clientId);
            setPrivacyData(response);
            setShowPrivacyBox(true);
        } catch (error) {
            console.error('Error fetching privacy data', error);
        }
    };

    const handleFollowAction = async () => {
        try {
            const clientId = localStorage.getItem('current_logged_user');
            await clientService.followAction(followUsername, clientId);
            alert('Follow action completed successfully');
            setShowFollowBox(false); // Hide the follow box
        } catch (error) {
            console.error('Error performing follow action', error);
        }
    };

    return (
        <div className="profile-container">
            <h1 className="profile-title">{"Twitter"}</h1>
            <h2 className="profile-username">{user.username}'s Profile</h2>
            <button className="profile-button" onClick={() => setShowEditBox(true)}>
                Edit Profile
            </button>
            <button className="profile-button" onClick={handlePrivacy}>Privacy</button>
            {showPrivacyBox && privacyData && (
                <div className="privacy-box">
                    <p>Username: {privacyData.username}</p>
                    <p>Email: {privacyData.email}</p>
                    <p>Role: {privacyData.role}</p>
                    {/* Add other UserDto details as needed */}
                </div>
            )}
            <button className="profile-button" onClick={() => setShowFollowers(!showFollowers)}>
                Followers ({followers.length})
            </button>
            {showFollowers && (
                <div className="followers-list">
                    {followers.map((follower, index) => (
                        <div key={index} className="white-text">{follower}</div>
                    ))}
                </div>
            )}
            <button className="profile-button" onClick={() => setShowFollowing(!showFollowing)}>
                Following ({following.length})
            </button>
            {showFollowing && (
                <div className="following-list">
                    {following.map((followee, index) => (
                        <div key={index} className="white-text">{followee}</div>
                    ))}
                </div>
            )}
            <button className="profile-button" onClick={handleDelete}>Delete Account</button>
            <button className="profile-button" onClick={() => {
                setShowAllClients(!showAllClients);
                if (!showAllClients) fetchAllClients();
            }}>
                Show All Users
            </button>
            {showAllClients && (
                <div className="all-clients-list" dangerouslySetInnerHTML={{ __html: allClients }} />
            )}
            {showEditBox && (
                <div className="edit-box">
                    <textarea
                        value={newBio}
                        onChange={(e) => setNewBio(e.target.value)}
                        placeholder="Enter new bio"
                    />
                    <button className="profile-button" onClick={handleUpdate}>Update Bio</button>
                </div>
            )}
            <button className="profile-button" onClick={() => setShowFollowBox(true)}>
                Follow/Unfollow User
            </button>
            {showFollowBox && (
                <div className="follow-box">
                    <input
                        type="text"
                        value={followUsername}
                        onChange={(e) => setFollowUsername(e.target.value)}
                        placeholder="Enter username to follow/unfollow"
                    />
                    <button className="profile-button" onClick={handleFollowAction}>Submit</button>
                </div>
            )}
            <button className="profile-button" onClick={() => navigate('/posts')}>
                Go to Posts Page
            </button>
        </div>
    );
};

export default Profile;
