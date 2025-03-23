import { useState } from "react";
import PropTypes from "prop-types";
import "./Counter.css";

export default function Counter() {
  const [count, setCount] = useState(0);

  function incrementCounterParentFunction(by) {
    setCount(count + by);
  }

  function decrementCounterParentFunction(by) {
    setCount(count - by);
  }

  return (
    <>
      <span className="totalCount">{count}</span>
      <CounterButton
        by={1}
        incrementMethod={incrementCounterParentFunction}
        decrementMethod={decrementCounterParentFunction}
      />
      <CounterButton
        by={3}
        incrementMethod={incrementCounterParentFunction}
        decrementMethod={decrementCounterParentFunction}
      />
      <CounterButton
        by={5}
        incrementMethod={incrementCounterParentFunction}
        decrementMethod={decrementCounterParentFunction}
      />
    </>
  );
}

function CounterButton({ by, incrementMethod, decrementMethod }) {
  const [count, setCount] = useState(0);

  function increment() {
    setCount(count + by);
    incrementMethod(by);
  }

  function decrement() {
    setCount(count - by);
    decrementMethod(by);
  }

  return (
    <div className="Counter">
      <span className="count">{count}</span>
      <div>
        <button className="counterButton" onClick={increment}>
          +{by}
        </button>
        <button className="counterButton" onClick={decrement}>
          -{by}
        </button>
      </div>
    </div>
  );
}

CounterButton.propTypes = {
  by: PropTypes.number,
};

CounterButton.defaultProps = {
  by: 1,
};
