import React, { Component } from 'react';
import { withRouter } from 'react-router-dom';
import Duplicates from '../components/Duplicates';

class HomePage extends Component {
  render() {
    return (
        <div className="home-page">
          <Duplicates />
        </div>
    );
  }
}

export default withRouter(HomePage);
