import enviroment from '../enviroment';

class ClientService {
  constructor() {
    this.token = localStorage.getItem('access_token');
  }

  followAccount(username, userWhoFollows) {
    const requestBody = {
      username: username,
      userWhoFollows: userWhoFollows
    };

    const requestOptions = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.token}`
      },
      body: JSON.stringify(requestBody)
    };

    return fetch(`${enviroment.CLIENT}${enviroment.FOLLOW}`, requestOptions)
      .then(response => {
        if (!response.ok) {
          throw new Error('Follow action could not be completed');
        }
        return response.json();
      })
      .catch(error => {
        console.error("There was a problem with the fetch operation", error);
        throw error;
      });
  }

  unfollowAccount(username, userWhoFollows) {
    const requestBody = {
      username: username,
      userWhoFollows: userWhoFollows
    };

    const requestOptions = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.token}`
      },
      body: JSON.stringify(requestBody)
    };

    return fetch(`${enviroment.CLIENT}${enviroment.FOLLOW}`, requestOptions)
      .then(response => {
        if (!response.ok) {
          throw new Error('Unfollow action could not be completed');
        }
        return response.json();
      })
      .catch(error => {
        console.error("There was a problem with the fetch operation", error);
        throw error;
      });
  }

  followAction(username, userWhoFollows) {
    const requestBody = {
      username: username,
      userWhoFollows: userWhoFollows
    };

    const requestOptions = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.token}`
      },
      body: JSON.stringify(requestBody)
    };

    return fetch(`${enviroment.CLIENT}${enviroment.FOLLOW}?username=${username}&userWhoFollows=${userWhoFollows}`, requestOptions)
      .then(response => {
        if (!response.ok) {
          throw new Error('Follow action could not be completed');
        }
      })
      .catch(error => {
        console.error("There was a problem with the fetch operation", error);
        throw error;
      });
  }

  getFollowers(clientId) {
    const requestOptions = {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.token}`
      }
    };

    return fetch(`${enviroment.CLIENT}${enviroment.SHOW_FOLLOWERS.replace("{id}", clientId)}`, requestOptions)
      .then(response => {
        if (!response.ok) {
          throw new Error('Failed to fetch followers');
        }
        return response.json();
      })
      .catch(error => {
        console.error("There was a problem with the fetch operation", error);
        throw error;
      });
  }

  getFollowing(clientId) {
    const requestOptions = {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.token}`
      }
    };

    return fetch(`${enviroment.CLIENT}${enviroment.SHOW_FOLLOWING.replace("{id}", clientId)}`, requestOptions)
      .then(response => {
        if (!response.ok) {
          throw new Error('Failed to fetch following accounts');
        }
        return response.json();
      })
      .catch(error => {
        console.error("There was a problem with the fetch operation", error);
        throw error;
      });
  }

  updateClientData(clientId, clientData) {
    const requestOptions = {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.token}`
      },
    };

    return fetch(`${enviroment.CLIENT}${enviroment.UPDATE.replace("{id}", clientId)}?str=${clientData}`, requestOptions)
      .then(response => {
        if (!response.ok) {
          throw new Error('Update action could not be completed');
        }
      })
      .catch(error => {
        console.error("There was a problem with the fetch operation", error);
        throw error;
      });
  }

  deleteClient(clientId) {
    const requestOptions = {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.token}`
      }
    };

    return fetch(`${enviroment.CLIENT}${enviroment.DELETE.replace("{id}", clientId)}`, requestOptions)
      .then(response => {
        if (!response.ok) {
          throw new Error('Delete action could not be completed');
        }
        return response.json();
      })
      .catch(error => {
        console.error("There was a problem with the fetch operation", error);
        throw error;
      });
  }

  getAllClients() {
    const requestOptions = {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.token}`
      }
    };
    return fetch(`${enviroment.CLIENT}/show`, requestOptions)
      .then(response => {
        if (!response.ok) {
          throw new Error('Failed to fetch clients');
        }
        return response.json()
      })
      .catch(error => {
        console.error("There was a problem with the fetch operation", error);
        throw error;
      });
  }

  getClientData(clientId) {
    const requestOptions = {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${this.token}`
      }
    };

    return fetch(`${enviroment.CLIENT}${enviroment.CLIENT_DATA.replace("{id}", clientId)}`, requestOptions)
      .then(response => {
        if (!response.ok) {
          throw new Error('Failed to fetch client data');
        }
        return response.json();
      })
      .catch(error => {
        console.error("There was a problem with the fetch operation", error);
        throw error;
      });
  }
}

export default ClientService;
