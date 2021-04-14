import React, { Component } from 'react';
import { getDuplicates } from '../actions/getDuplicates';

class Duplicates extends Component {
  constructor(props) {
    super(props);
    this.state = {
      data: 'unable to fetch data'
    };
  }

  componentDidMount() {
    this._isMounted = true;
    getDuplicates().then(output => {
        if (this._isMounted) {
          this.setState({data: output})
          console.log("got it");
        }
      }
    )
  }

  render() {
    if (this._isMounted) {
      let style = 'span {display: block;}';
      let uniqueItems = JSON.parse(this.state.data.unique);
      let duplicateItems = JSON.parse(this.state.data.unique);
      return (
        <div>{
          Object.keys(uniqueItems).map((key) => (
            <span>{uniqueItems[key]}</span>
          ))
        }
        <style>{style}</style>
        </div>
      );
    } else {
      return (<div>{this.state.data.unique}</div>);
    }

  }
}

export default Duplicates;
