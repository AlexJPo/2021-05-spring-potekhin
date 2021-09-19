import React from 'react'
import { Link } from 'react-router-dom';

const Header = (props) => (
  <h1>{props.title}</h1>
);

export default class Books extends React.Component {

  constructor() {
    super();
    this.state = { books: [] };
  }

  componentDidMount() {
    fetch('/book/')
      .then(response => response.json())
      .then(books => this.setState({books}));
  }

  render() {
    return (
      <React.Fragment>
        <ul>
          <li>
            <a href='/'>Home</a>
          </li>
          <li>
            <a href='/books/create'>Создать</a>
          </li>
        </ul>

        <Header title={'Books'}/>
        <table>
          <thead>
          <tr>
            <th>#</th>
            <th>Title</th>
            <th>Action</th>
          </tr>
          </thead>
          <tbody>
          {
            this.state.books.map((book, i) => (
              <tr key = {i}>
                <td>{ book.id }</td>
                <td>{ book.title }</td>
                <td>
                  <button tag={Link} to={"/books/" + book.id}>Edit</button>
                  <a href={'/books/' + book.id}>Создать</a>
                </td>
              </tr>
            ))
          }
          </tbody>
        </table>
        <button to="/books/create">Create new</button>
      </React.Fragment>
    )
  }
};
