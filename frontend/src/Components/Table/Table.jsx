import React from "react";
import Button from "../Button/Button";

function Table({ headers, data, onRemoveClick, onViewDetailClick }) {
  return (
    <table>
      <thead>
        <tr className="ticket-table-row">
          {headers.map((header, index) => (
            <th key={index} className="ticket-table-head">
              {header}
            </th>
          ))}
        </tr>
      </thead>
      <tbody>
        {data.map((item, index) => (
          <tr key={index}>
            {item.map((cell, cellIndex) => (
              <td key={cellIndex} className="ticket-table-data">
                {cell}
              </td>
            ))}
            {onRemoveClick && (
              <td className="ticket-table-data">
                <Button
                  className="delete-btn"
                  onClick={() => onRemoveClick(index)}
                  text="Delete"
                />
              </td>
            )}
          </tr>
        ))}
      </tbody>
    </table>
  );
}

export default Table;
