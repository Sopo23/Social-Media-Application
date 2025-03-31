import React, { useState, useEffect } from 'react';
import PostService from '../Service/PostService';
import { useNavigate } from 'react-router-dom';
import '../App.css';

const PostPage = () => {
    const [posts, setPosts] = useState([]);
    const [replies, setReplies] = useState([]);
    const [showPosts, setShowPosts] = useState(false);
    const [showReplies, setShowReplies] = useState(false);
    const [showCreateBox, setShowCreateBox] = useState(false);
    const [showEditBox, setShowEditBox] = useState(false);
    const [showReplyBox, setShowReplyBox] = useState(false);
    const [newPostContent, setNewPostContent] = useState("");
    const [newPostImage, setNewPostImage] = useState("");
    const [editPostId, setEditPostId] = useState(null);
    const [replyPostId, setReplyPostId] = useState(null);
    const [replyContent, setReplyContent] = useState("");
    const postService = new PostService();
    const navigate = useNavigate();

    useEffect(() => {
        fetchAllPosts();
    }, []);

    const fetchAllPosts = async () => {
        try {
            const response = await postService.showAllPostsWithoutParent();
            setPosts(response);
        } catch (error) {
            console.error('Error fetching posts', error);
        }
    };

    const fetchReplies = async (postId) => {
        try {
            const response = await postService.showReplysOfAPost(postId);
            setReplies(response);
        } catch (error) {
            console.error('Error fetching replies', error);
        }
    };

    const handleCreatePost = async () => {
        const postData = {
            post: newPostContent,
            image: newPostImage,
            clientName: localStorage.getItem('current_logged_user')
        };

        try {
            await postService.createPost(postData);
            alert('Post created successfully');
            fetchAllPosts();
            setShowCreateBox(false);
        } catch (error) {
            console.error('Error creating post', error);
        }
    };

    const handleEditPost = async () => {
        try {
            await postService.editPost(editPostId, newPostContent, newPostImage);
            alert('Post edited successfully');
            fetchAllPosts();
            setShowEditBox(false);
        } catch (error) {
            console.error('Error editing post', error);
        }
    };

    const handleLikePost = async (postId) => {
        try {
            await postService.likePost(postId);
            alert('Post liked successfully');
            fetchAllPosts();
        } catch (error) {
            console.error('Error liking post', error);
        }
    };

    const handleAddReply = async () => {
        const replyData = {
            post: replyContent,
            clientName: localStorage.getItem('current_logged_user')
        };

        try {
            await postService.addReply(replyPostId, replyData);
            alert('Reply added successfully');
            fetchReplies(replyPostId);
            setShowReplyBox(false);
        } catch (error) {
            console.error('Error adding reply', error);
        }
    };

    return (
        <div className="post-page-container">
            <h1 className="post-page-title">Post Management</h1>
            <button className="post-page-button" onClick={() => setShowCreateBox(true)}>
                Create Post
            </button>
            <button className="post-page-button" onClick={() => setShowPosts(!showPosts)}>
                Show All Posts
            </button>
            {showPosts && (
                <div className="posts-list">
                    {posts.map((post, index) => (
                        <div key={index} className="post-item">
                            <div>{post.post}</div>
                            <button className="post-page-button" onClick={() => {
                                setEditPostId(post.id);
                                setShowEditBox(true);
                            }}>
                                Edit
                            </button>
                            <button className="post-page-button" onClick={() => handleLikePost(post.id)}>
                                Like 
                            </button>
                            <button className="post-page-button" onClick={() => {
                                fetchReplies(post.id);
                                setShowReplies(true);
                            }}>
                                Show Replies
                            </button>
                            <button className="post-page-button" onClick={() => {
                                setReplyPostId(post.id);
                                setShowReplyBox(true);
                            }}>
                                Add Reply
                            </button>
                        </div>
                    ))}
                </div>
            )}
            {showReplies && (
                <div className="replies-list">
                    {replies.map((reply, index) => (
                        <div key={index} className="reply-item">
                            <div>{reply.post}</div>
                        </div>
                    ))}
                </div>
            )}
            {showCreateBox && (
                <div className="create-box">
                    <textarea
                        value={newPostContent}
                        onChange={(e) => setNewPostContent(e.target.value)}
                        placeholder="Enter post content"
                    />
                    <input
                        type="text"
                        value={newPostImage}
                        onChange={(e) => setNewPostImage(e.target.value)}
                        placeholder="Enter post image URL"
                    />
                    <button className="post-page-button" onClick={handleCreatePost}>Create Post</button>
                </div>
            )}
            {showEditBox && (
                <div className="edit-box">
                    <textarea
                        value={newPostContent}
                        onChange={(e) => setNewPostContent(e.target.value)}
                        placeholder="Edit post content"
                    />
                    <input
                        type="text"
                        value={newPostImage}
                        onChange={(e) => setNewPostImage(e.target.value)}
                        placeholder="Edit post image URL"
                    />
                    <button className="post-page-button" onClick={handleEditPost}>Update Post</button>
                </div>
            )}
            {showReplyBox && (
                <div className="reply-box">
                    <textarea
                        value={replyContent}
                        onChange={(e) => setReplyContent(e.target.value)}
                        placeholder="Enter reply content"
                    />
                    <button className="post-page-button" onClick={handleAddReply}>Add Reply</button>
                </div>
            )}
        </div>
    );
};

export default PostPage;
