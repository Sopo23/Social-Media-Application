import enviroment from '../enviroment';

class AuthService {

  login(username, password) {

    const requestBody = {
      username: username,
      password: password
    };

    const requestOptions = {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(requestBody)
    };

    return fetch(enviroment.sign_in_Url, requestOptions)
      .then(response => {
        if (!response.ok) {
          throw new Error('Login could not be done')
        }
        return response.json();
      })
      .then(
        data => {
          console.log(data.id);
          localStorage.setItem("access_token", data.token)
          localStorage.setItem("current_logged_user", data.id)

          return data;
        }
      )
      .catch(error => {
        console.error("There was a problem with the fetch operation", error);
        throw error;
      }
      )
  }

  getToken() {
    return localStorage.getItem('access_token')
  }

  register(username, password,email) {

    const requestBody = {
      username: username,
      password: password,
      email: email,
      role: "CUSTOMER"
    };

    const requestOptions = {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(requestBody)
    };

    return fetch(enviroment.sign_up_Url, requestOptions)
      .then(response => {
        if (!response.ok) {
          throw new Error('Register could not be done')
        }
        return response.json();
      })
      .then(
        data => {
          console.log(data)
          return data;
        }
      )
      .catch(error => {
        console.error("There was a problem with the fetch operation", error);
        throw error;
      }
      )
  }

  viewUserPrivacy(userId) {
    const token = this.getToken();
    const requestOptions = {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
      }
    };

    return fetch(`${enviroment.authUrl}${enviroment.VIEW_PRIVACY.replace("{id}", userId)}`, requestOptions)
      .then(response => {
        if (!response.ok) {
          throw new Error('Failed to fetch user privacy data');
        }
        return response.json();
      })
      .catch(error => {
        console.error("There was a problem with the fetch operation", error);
        throw error;
      });
  }

}

export default AuthService