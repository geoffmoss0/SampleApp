import React, { Component } from 'react';
import { getDuplicates } from '../actions/getDuplicates';

class Duplicates extends Component {
  constructor(props) {
    super(props);
    this.state = {
      message: 'No message from server'
    };
  }

  render() {
    return (
      <div>{getDuplicates()}</div>
    );
  }
}

export default Duplicates;
