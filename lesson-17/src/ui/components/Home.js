import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { Container } from 'reactstrap';

class Home extends Component {
  render() {
    return (
      <div>
        <Container fluid>
          <Link to="/books">Книги</Link>
          <Link to="/authors">Авторы</Link>
          <Link to="/genres">Жанры</Link>
        </Container>
      </div>
    );
  }
}

export default Home;