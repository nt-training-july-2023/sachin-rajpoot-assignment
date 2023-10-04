import React from 'react';

function InputField({
  id,
  name,
  type,
  placeholder,
  value,
  onChange,
  required,
  error,
}) {
  return (
      <input
        type={type}
        id={id}
        name={name}
        placeholder={placeholder}
        value={value}
        onChange={onChange}
        required={required}
      />
  );
}

export default InputField;
