const PHONE_LIST_ID = "phones";
const list = document.getElementById(PHONE_LIST_ID);

const createInput = ({ type, ...props }) => {
  const input = document.createElement("input");
  input.type = type || "text";
  Object.keys(props).forEach((e) => (input[e] += " " + props[e]));
  return input;
};

function newPhoneEntry() {
  const _newIdx = list.children.length;

  const entry = document.createElement("div");

  const idInput = createInput({ type: "hidden", nam: `phones[${_newIdx}].id` });
  entry.appendChild(idInput);

  const codeInput = createInput({
    name: `phones[${_newIdx}].code`,
    required: true,
  });
  entry.appendChild(codeInput);

  const valueInput = createInput({
    name: `phones[${_newIdx}].number`,
    required: true,
  });
  entry.appendChild(valueInput);

  const removeButton = document.createElement("button");
  removeButton.type = "button";
  removeButton.innerText = "remove";
  removeButton.setAttribute("onclick", "removeEntry(" + _newIdx + ")");
  entry.appendChild(removeButton);
  list.appendChild(entry);
}

function removeEntry(idx) {
  const entry = list.children.item(idx);
  if (entry) list.removeChild(entry);
}
