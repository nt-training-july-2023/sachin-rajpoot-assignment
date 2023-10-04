import React from 'react';

function Label({ htmlFor, text }) {
  return (
    <label htmlFor={htmlFor}>{text}:</label>
  );
}

export default Label;