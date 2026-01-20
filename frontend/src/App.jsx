import { useEffect, useState } from "react";

function App() {
  const [inventory, setInventory] = useState(0);
  const [loading, setLoading] = useState(false);
  const [message, setMessage] = useState("");

  useEffect(() => {
    fetch("http://localhost:8080/inventory")
      .then(res => res.json())
      .then(setInventory);
  }, []);

  const buy = async () => {
    setLoading(true);
    setMessage("");

    try {
      const res = await fetch("http://localhost:8080/buy", {
        method: "POST",
      });

      if (res.ok) {
        setInventory(prev => prev - 1);
        setMessage("🎉 Purchase successful");
      } else {
        setMessage("❌ Sold out");
      }
    } catch {
      setMessage("⚠️ Network error");
    } finally {
      setLoading(false);
    }
  };

  // const resetInventory = async () => {
  //   setLoading(true);
  //   setMessage("");

  //   try {
  //     await fetch("http://localhost:8080/reset", {
  //       method: "POST",
  //     });

  //     setInventory(100);
  //     setMessage("🔄 Inventory reset to 100");
  //   } catch {
  //     setMessage("⚠️ Failed to reset inventory");
  //   } finally {
  //     setLoading(false);
  //   }
  // };

  return (
    <div style={{ padding: 20 }}>
      <h1>Golden Ticket</h1>

      <p>Items Remaining: {inventory}</p>

      <button
        onClick={buy}
        disabled={loading || inventory === 0}
      >
        {loading ? "Processing..." : "Buy Now"}
      </button>

      <p>{message}</p>

      <br />

      {/* <button
        onClick={resetInventory}
        disabled={loading}
      >
        Reset Inventory
      </button> */}
    </div>
  );
}

export default App;
