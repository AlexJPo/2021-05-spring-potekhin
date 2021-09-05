import React from 'react';

import Home from './Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import Books from './books/Books';
import Genres from './Genres';
import Authors from './Authors';
import CreateBooks from './books/CreateBooks'

class App extends React.Component {

  constructor() {
    super();
    //this.state = {persons: []};
  }

  componentDidMount() {
    /*fetch('/api/persons')
      .then(response => response.json())
      .then(persons => this.setState({persons}));*/
  }

  render() {
    return (
      <Router>
        <Switch>
          <Route path='/' exact={true} component={Home}/>

          <Route path='/books' exact={true} component={Books}/>
          <Route path='/books/:id' component={CreateBooks}/>
          <Route path='/books/create' component={CreateBooks}/>

          <Route path='/genres' exact={true} component={Genres}/>
          <Route path='/authors' exact={true} component={Authors}/>
        </Switch>
      </Router>
    )
  }
}

export default App;
