import enviroment from '../enviroment';

class PostService {
  constructor() {
    this.token = localStorage.getItem('access_token');
  }

  createPost(postData) {
    const requestOptions = {
      method: 'POST',
      headers: { 
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.token}`
      },
      body: JSON.stringify(postData)
    };

    return fetch(`${enviroment.POSTARE}${enviroment.CREATE_POST}`, requestOptions)
      .then(response => {
        if (!response.ok) {
          throw new Error('Create post action could not be completed');
        }
      })
      .catch(error => {
        console.error("There was a problem with the fetch operation", error);
        throw error;
      });
  }

  editPost(postId, newPost, newPoza) {
    const requestBody = {
      newPost,
      newPoza
    };

    const requestOptions = {
      method: 'PUT',
      headers: { 
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.token}`
      },
      body: JSON.stringify(requestBody)
    };

    return fetch(`${enviroment.POSTARE}${enviroment.EDIT.replace("{id}", postId)}?newPost=${newPost}&newPoza=${newPoza}`, requestOptions)
      .then(response => {
        if (!response.ok) {
          throw new Error('Edit post action could not be completed');
        }
      })
      .catch(error => {
        console.error("There was a problem with the fetch operation", error);
        throw error;
      });
  }

  likePost(postId) {
    const requestOptions = {
      method: 'PUT',
      headers: { 
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.token}`
      }
    };

    return fetch(`${enviroment.POSTARE}${enviroment.LIKE.replace("{id}", postId)}`, requestOptions)
      .then(response => {
        if (!response.ok) {
          throw new Error('Like post action could not be completed');
        }
      })
      .catch(error => {
        console.error("There was a problem with the fetch operation", error);
        throw error;
      });
  }

  addReply(postId, replyData) {
    const requestOptions = {
      method: 'PUT',
      headers: { 
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.token}`
      },
      body: JSON.stringify(replyData)
    };

    return fetch(`${enviroment.POSTARE}${enviroment.REPLY.replace("{id}", postId)}`, requestOptions)
      .then(response => {
        if (!response.ok) {
          throw new Error('Add reply action could not be completed');
        }
      })
      .catch(error => {
        console.error("There was a problem with the fetch operation", error);
        throw error;
      });
  }

  showAllPostsWithoutParent() {
    const requestOptions = {
      method: 'GET',
      headers: { 
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.token}`
      }
    };

    return fetch(`${enviroment.POSTARE}${enviroment.SHOW_POST}`, requestOptions)
      .then(response => {
        if (!response.ok) {
          throw new Error('Failed to fetch posts without parent');
        }
        return response.json();
      })
      .catch(error => {
        console.error("There was a problem with the fetch operation", error);
        throw error;
      });
  }

  showReplysOfAPost(postId) {
    const requestOptions = {
      method: 'GET',
      headers: { 
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.token}`
      }
    };

    return fetch(`${enviroment.POSTARE}${enviroment.SHOW_REPLYS.replace("{id}", postId)}`, requestOptions)
      .then(response => {
        if (!response.ok) {
          throw new Error('Failed to fetch replies for the post');
        }
        return response.json();
      })
      .catch(error => {
        console.error("There was a problem with the fetch operation", error);
        throw error;
      });
  }

  deletePost(postId) {
    const requestOptions = {
      method: 'DELETE',
      headers: { 
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.token}`
      }
    };

    return fetch(`${enviroment.POSTARE}${enviroment.DELETE.replace("{id}", postId)}`, requestOptions)
      .then(response => {
        if (!response.ok) {
          throw new Error('Delete post action could not be completed');
        }
        return response.json();
      })
      .catch(error => {
        console.error("There was a problem with the fetch operation", error);
        throw error;
      });
  }
}

export default PostService;
