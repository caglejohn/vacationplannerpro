import { render } from '@testing-library/react';
import LogIn from '../pages/LogIn';
import { BrowserRouter, Router } from 'react-router-dom';

describe('LogIn', () => {
  test('That company logo displays on log in page', () => {
    const { getByAltText } = render(
      <BrowserRouter>
        <LogIn />
      </BrowserRouter>,
    );
    const image = getByAltText('Company Logo');

    expect(image).toBeInTheDocument();
    expect(image.src).toContain('/logo.jpeg');
  });
});
