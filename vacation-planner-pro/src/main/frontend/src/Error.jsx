import { useRouteError } from 'react-router-dom';
import { Container, Row, Col, Button } from 'react-bootstrap';

export default function Error() {
  const error = useRouteError();
  console.error(error);

  return (
    <Container className="mt-5">
      <Row className="justify-content-center">
        <Col md={8}>
          <div className="text-center">
            <h1 className="display-4">Oops!</h1>
            <p className="lead">Sorry, an unexpected error has occurred.</p>
            <p>
            <i>{error.statusText || error.message}</i>
            </p>
            <Button variant="primary" href="/" className="mt-3">Home</Button>
          </div>
        </Col>
      </Row>
    </Container>
  );
}
