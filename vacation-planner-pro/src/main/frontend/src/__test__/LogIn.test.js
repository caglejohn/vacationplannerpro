import { render } from '@testing-library/react';
import LogIn from '../components/LogIn';

describe('LogIn', () => {
  test('That company logo displays on log in page', () => {
    const { getByAltText } = render(<LogIn />);
    const image = getByAltText('Company Logo');

    expect(image).toBeInTheDocument();
    expect(image.src).toContain('/logo.jpeg');
  });
});
