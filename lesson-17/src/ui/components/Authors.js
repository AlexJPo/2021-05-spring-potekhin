import React from 'react'

const Header = (props) => (
  <h1>{props.title}</h1>
);

class Authors extends React.Component {

  constructor() {
    super();
    this.state = { authors: [] };
  }

  componentDidMount() {
    fetch('/author/')
      .then(response => response.json())
      .then(authors => this.setState({authors}));
  }

  render() {
    return (
      <React.Fragment>
        <a href='/'>Home</a>
        <Header title={'Authors'}/>
        <table>
          <thead>
          <tr>
            <th>#</th>
            <th>Title</th>
          </tr>
          </thead>
          <tbody>
          {
            this.state.authors.map((author, i) => (
              <tr key = {i}>
                <td>{ i + 1 }</td>
                <td>{ author.name }</td>
              </tr>
            ))
          }
          </tbody>
        </table>
      </React.Fragment>
    )
  }
}

export default Authors;
