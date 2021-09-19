import React from 'react'

const Header = (props) => (
  <h1>{props.title}</h1>
);

class Genres extends React.Component {
  constructor() {
    super();
    this.state = { genres: [] };
  }

  componentDidMount() {
    fetch('/genre/')
      .then(response => response.json())
      .then(genres => this.setState({genres}));
  }

  render() {
    return (
      <React.Fragment>
        <a href='/'>Home</a>
        <Header title={'Genres'}/>
        <table>
          <thead>
          <tr>
            <th>#</th>
            <th>Title</th>
          </tr>
          </thead>
          <tbody>
          {
            this.state.genres.map((genre, i) => (
              <tr key = {i}>
                <td>{ i + 1 }</td>
                <td>{ genre.title }</td>
              </tr>
            ))
          }
          </tbody>
        </table>
      </React.Fragment>
    )
  }
}

export default Genres;
