import React from 'react'
import {
  Button,
  Container,
  Form,
  FormGroup,
  Input,
  Label
} from 'reactstrap';
import { Link } from 'react-router-dom';

class CreateBooks extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      item: {}
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  componentDidMount() {
    //const book = await (await fetch(`/book/${this.props.match.params.id}`)).json();
    //this.setState({item: book});

    const book = fetch(`/book/${this.props.match.params.id}`).json();
    this.setState({item: book});
  }

  handleChange() {

  }

  handleSubmit() {

  }

  render() {
    const {item} = this.state;

    return (
      <Container>
        <h2>{'Создание книги'}</h2>
        <Form onSubmit={this.handleSubmit}>
          <FormGroup>
            <Label for="name">Название</Label>
            <Input type="text" name="name" id="name" value={item.title || ''}
                   onChange={this.handleChange} />
          </FormGroup>
          /*<FormGroup>
            <Label for="address">Address</Label>
            <Input type="text" name="address" id="address" value={item.address || ''}
                   onChange={this.handleChange} autoComplete="address-level1"/>
          </FormGroup>
          <FormGroup>
            <Label for="city">City</Label>
            <Input type="text" name="city" id="city" value={item.city || ''}
                   onChange={this.handleChange} autoComplete="address-level1"/>
          </FormGroup>
          <div className="row">
            <FormGroup className="col-md-4 mb-3">
              <Label for="stateOrProvince">State/Province</Label>
              <Input type="text" name="stateOrProvince" id="stateOrProvince" value={item.stateOrProvince || ''}
                     onChange={this.handleChange} autoComplete="address-level1"/>
            </FormGroup>
            <FormGroup className="col-md-5 mb-3">
              <Label for="country">Country</Label>
              <Input type="text" name="country" id="country" value={item.country || ''}
                     onChange={this.handleChange} autoComplete="address-level1"/>
            </FormGroup>
            <FormGroup className="col-md-3 mb-3">
              <Label for="country">Postal Code</Label>
              <Input type="text" name="postalCode" id="postalCode" value={item.postalCode || ''}
                     onChange={this.handleChange} autoComplete="address-level1"/>
            </FormGroup>
          </div>*/
          <FormGroup>
            <Button color="primary" type="submit">Сохранить</Button>{' '}
            <Button color="secondary" tag={Link} to="/books">Отмена</Button>
          </FormGroup>
        </Form>
      </Container>
    );
  }
}

export default CreateBooks;
